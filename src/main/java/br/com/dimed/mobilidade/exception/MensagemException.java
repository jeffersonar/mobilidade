package br.com.dimed.mobilidade.exception;

import java.time.LocalDateTime;
import java.util.List;

public class MensagemException {

	 private String mensagem;
	    private int code;
	    private String status;
	    private String nomeObject;
	    private LocalDateTime data;
	    private List<GenericoException> errors;

	    public MensagemException() {
	    }

	    public MensagemException(String mensagem, int code, String status, String nomeObject, List<GenericoException> errors) {
	        this.mensagem = mensagem;
	        this.code = code;
	        this.status = status;
	        this.nomeObject = nomeObject;
	        this.errors = errors;
	        this.data =LocalDateTime.now();
	    }

	    public LocalDateTime getData() {
			return data;
		}

		public void setData(LocalDateTime data) {
			this.data = data;
		}

		public String getMensagem() {
	        return mensagem;
	    }

	    public void setMensagem(String mensagem) {
	        this.mensagem = mensagem;
	    }

	    public int getCode() {
	        return code;
	    }

	    public void setCode(int code) {
	        this.code = code;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getNomeObject() {
	        return nomeObject;
	    }

	    public void setNomeObject(String nomeObject) {
	        this.nomeObject = nomeObject;
	    }

	    public List<GenericoException> getErrors() {
	        return errors;
	    }

	    public void setErrors(List<GenericoException> errors) {
	        this.errors = errors;
	    }
}
