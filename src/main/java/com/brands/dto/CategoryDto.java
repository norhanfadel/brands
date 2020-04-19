package com.brands.dto;

import com.brands.dao.Category;

public interface CategoryDto {
    Category getCategoryByName(String name);
    Category getCategoryById(int id);
}
