package com.example.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.dto.HomeSearchDTO;
import com.example.entity.ProductEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.example.repository.custom.ProductRepositoryCustom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<ProductEntity> findAll(HomeSearchDTO homeSearchDTO) {
		try {
			StringBuilder sql = new StringBuilder("FROM ProductEntity AS p");
			sql.append(" WHERE 1=1");
			if (StringUtils.isNotBlank(homeSearchDTO.getName())) {
				sql.append(" AND LOWER(p.name) LIKE LOWER('%" + homeSearchDTO.getName().toLowerCase() + "%')");
			}
			if (StringUtils.isNotBlank(homeSearchDTO.getProductCategoryCode())) {
				sql.append(" AND p.productCategory.code = '"+homeSearchDTO.getProductCategoryCode()+"'");
			}
			if (StringUtils.isNotBlank(homeSearchDTO.getBrandCode())) {
				sql.append(" AND p.brand.code = '"+homeSearchDTO.getBrandCode()+"'");
			}
			if (StringUtils.isNotBlank(homeSearchDTO.getSearchByCost())) {
				switch (homeSearchDTO.getSearchByCost()) {
					case "UNDER_2M":
						sql.append(" AND p.price <= 2000000");
						break;
					case "2M_4M":
						sql.append(" AND (p.price >= 2000000 AND p.price <= 4000000)");
						break;
					case "4M_7M":
						sql.append(" AND (p.price >= 4000000 AND p.price <= 7000000)");
						break;
					case "7M_13M":
						sql.append(" AND (p.price >= 7000000 AND p.price <= 13000000)");
						break;
				}
			}
			if (homeSearchDTO.getFeatureSearches() != null && homeSearchDTO.getFeatureSearches().length > 0) {
				for(String feature: homeSearchDTO.getFeatureSearches()) {
					sql.append(" AND p.feature LIKE '%"+feature+"%'");
				}
			}
			if (StringUtils.isNotBlank(homeSearchDTO.getSortBy())) {
				sql.append(" ORDER BY p.price "+homeSearchDTO.getSortBy()+"");
			}
			Query query = entityManager.createQuery(sql.toString());
			return query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
}

