package com.bookambulance.Interfaces;

import java.util.List;

public interface DataInterface <T,I>{
T saveOrUpdateData(T data);
void deleteDataById(I id);
T findById(I id);
List<T> getAllData();
}
