package com.formation.computerdatabase.binding.dto;

import java.util.List;

public interface DTO<T> {
	DTO<T> map(T t);
	List<DTO<T>> mapListe(List<T> list);
}
