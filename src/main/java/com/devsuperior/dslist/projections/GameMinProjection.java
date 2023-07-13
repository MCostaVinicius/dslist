package com.devsuperior.dslist.projections;

public interface GameMinProjection {
	
	//metodos get correspondentes a minha consulta
	
	Long getId();
	String getTitle();
	Integer getGameYear();
	String getImgUrl();
	String getShortDescription();
	Integer getPosition();

	
}
