package com.challenge.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {

    private LocalDateTime dataTime;
    private String mensagem;
    private String detalhes;
    
	public ExceptionResponseDto(LocalDateTime dataTime, String mensagem, String detalhes) {
		this.dataTime = dataTime;
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public LocalDateTime getDataTime() {
		return dataTime;
	}

	public void setDataTime(LocalDateTime dataTime) {
		this.dataTime = dataTime;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
    
	
    
}
