package com.example.service.impl;

import com.example.converter.ProductConverter;
import com.example.dto.CommentDTO;
import com.example.dto.HomeSearchDTO;
import com.example.dto.ProductDTO;
import com.example.entity.CommentEntity;
import com.example.entity.EventEntity;
import com.example.entity.ProductEntity;
import com.example.entity.UserEntity;
import com.example.repository.*;
import com.example.util.SecurityUtils;
import com.example.util.UploadFileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.service.IProductService;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.codec.binary.Base64;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<ProductDTO> findAll(String name, Pageable pageable) {
        List<ProductEntity> results = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            results = productRepository.findByNameContainingIgnoreCase(name, pageable).getContent();
        } else {
            results = productRepository.findAll(pageable).getContent();
        }
        return results.stream().map(item -> productConverter.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAll(HomeSearchDTO homeSearchDTO) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll(homeSearchDTO);
        for (ProductEntity productEntity: productEntities) {
            ProductDTO productDTO = productConverter.convertToDto(productEntity);
            if (StringUtils.isNotBlank(productEntity.getImages())) {
                productDTO.setThumbnail(productEntity.getImages().split(",")[0]);
            }
            if (!productEntity.getEvent().getCode().equals("none")) {
                Integer basePrice = productEntity.getPrice();
                Integer percentDiscount = productEntity.getEvent().getPercent();
                Integer priceAfterDiscount = basePrice - ((basePrice * percentDiscount) / 100);
                productDTO.setPriceDiscount(priceAfterDiscount);
            }
            results.add(productDTO);
        }
        return results;
    }

    @Override
    public int getTotalItems(String name) {
        if (StringUtils.isNotBlank(name)) {
            return (int) productRepository.countByNameContainingIgnoreCase(name);
        } else {
            return (int) productRepository.count();
        }
    }

    @Override
    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.convertToEntity(productDTO);
        productEntity.setProductCategory(productCategoryRepository.findOneByCode(productDTO.getProductCategoryCode()));
        productEntity.setBrand(brandRepository.findOneByCode(productDTO.getBrandCode()));
        EventEntity event = eventRepository.findOneByCode(productDTO.getEventCode());
        productEntity.setEvent(event);
        saveImages(productDTO, productEntity);
        if (productDTO.getFeatureArrays() != null && productDTO.getFeatureArrays().length > 0) {
            productEntity.setFeature(String.join(",", productDTO.getFeatureArrays()));
        }
        productEntity = productRepository.save(productEntity);
        return productConverter.convertToDto(productEntity);
    }

    private void saveImages(ProductDTO productDTO, ProductEntity productEntity) {
        if (productDTO.getBase64Images() != null && productDTO.getBase64Images().length > 0) {
           List<String> images = new ArrayList<>();
           for (Map.Entry<String, String> entry : productDTO.getImageMaps().entrySet()) {
                String path = "/product" + "/" + entry.getKey();
                if (productEntity.getImages() != null) {
                    if (!productEntity.getImages().contains(path)) {
                        File file = new File("/home/electricstore" + path);
                        file.delete();
                    }
                }
                byte[] bytes = Base64.decodeBase64(entry.getValue().getBytes());
                uploadFileUtils.writeOrUpdate(path, bytes);
               images.add(path);
           }
           productEntity.setImages(String.join(",", images));
        }
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        try {
            ProductEntity existsProduct = productRepository.findOne(productDTO.getId());
            ProductEntity updateProduct = productConverter.convertToEntity(productDTO);
            updateProduct.setCreatedBy(existsProduct.getCreatedBy());
            updateProduct.setCreatedDate(existsProduct.getCreatedDate());
            updateProduct.setProductCategory(productCategoryRepository.findOneByCode(productDTO.getProductCategoryCode()));
            saveImages(productDTO, updateProduct);
            if (productDTO.getFeatureArrays() != null && productDTO.getFeatureArrays().length > 0) {
                updateProduct.setFeature(String.join(",", productDTO.getFeatureArrays()));
            }
            if (productDTO.getBase64Images() == null) {
                if (StringUtils.isNotBlank(existsProduct.getImages())) {
                    updateProduct.setImages(existsProduct.getImages());
                }
            }
            EventEntity event = eventRepository.findOneByCode(productDTO.getEventCode());
            updateProduct.setEvent(event);
            updateProduct.setBrand(brandRepository.findOneByCode(productDTO.getBrandCode()));
            productRepository.save(updateProduct);
            return productConverter.convertToDto(updateProduct);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteProduct(long[] ids) {
        for (long item : ids) {
            productRepository.delete(item);
        }
    }

    @Override
    @Transactional
    public void saveComment(CommentDTO comment) {
        String userName = SecurityUtils.getPrincipal().getUsername();
        UserEntity user = userRepository.findOneByUserNameAndStatus(userName, 1);
        ProductEntity product = productRepository.findOne(comment.getProductId());
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(comment.getContent());
        commentEntity.setProductComment(product);
        commentEntity.setUserComment(user);
        commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentDTO> findComment(long productId) {
        List<CommentDTO> results = new ArrayList<>();
        List<CommentEntity> comments = commentRepository.findByProductComment_Id(productId);
        for (CommentEntity item: comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent(item.getContent());
            commentDTO.setFullName(item.getUserComment().getFullName());
            results.add(commentDTO);
        }
        return results;
    }

    @Override
    public Map<String, String> getEvents() {
        Map<String, String> results = new HashMap<>();
        eventRepository.findAll().forEach(item -> results.put(item.getCode(), item.getName()));
        return results;
    }

    @Override
    public ProductDTO findById(long id) {
        ProductEntity productEntity = productRepository.findOne(id);
        ProductDTO productDTO = productConverter.convertToDto(productEntity);
        if (StringUtils.isNotBlank(productDTO.getImages())) {
            productDTO.setImageProducts(Arrays.asList(productDTO.getImages().split(",")));
        }
        if (StringUtils.isNotBlank(productEntity.getFeature())) {
            productDTO.setFeatureArrays(productEntity.getFeature().split(","));
        }
        productDTO.setEventCode(productEntity.getEvent().getCode());
        if (!productEntity.getEvent().getCode().equals("none")) {
            Integer basePrice = productEntity.getPrice();
            Integer percentDiscount = productEntity.getEvent().getPercent();
            Integer priceAfterDiscount = basePrice - ((basePrice * percentDiscount) / 100);
            productDTO.setPriceDiscount(priceAfterDiscount);
        }
        return productDTO;
    }
}
