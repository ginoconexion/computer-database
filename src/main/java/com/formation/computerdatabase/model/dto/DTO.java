package com.formation.computerdatabase.model.dto;

import java.util.List;

public interface DTO<T> {
	
	DTO<T> map(T t);
	List<DTO<T>> mapListe(List<T> list);
}
