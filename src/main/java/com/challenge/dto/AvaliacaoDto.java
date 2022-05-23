package com.challenge.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.challenge.enums.StatusAcesso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvaliacaoDto {
	@NotNull(message = "A nota é obrigatória")
	@Min(value = 5, message = "As notas deverão estar no intervalo entre 5 à 10")
	@Max(value = 10, message = "As notas deverão estar no intervalo entre 5 à 10")
	private Long nota;
	
	@NotNull(message = "As estretas são obrigatórias")
	@Min(value = 1, message = "As estretas permitidas são de 1 à 5")
	@Max(value = 5, message = "As estretas permitidas são de 1 à 5")
	private Integer estrela;
	
	@NotBlank(message = "É necessário informar se é PUBLICO ou PRIVADO")
	private StatusAcesso status;
	
	@NotNull(message = "É informar o imdbId do filme")
	private String imdbID;

}
