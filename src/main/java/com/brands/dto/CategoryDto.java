package com.brands.dto;

import com.brands.dao.Category;

public interface CategoryDto {
    public Category getCategoryByName(String name);
    public Category getCategoryById(int id);
}
