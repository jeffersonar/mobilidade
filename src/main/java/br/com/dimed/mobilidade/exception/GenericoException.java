package br.com.dimed.mobilidade.exception;

public class GenericoException {
	 private String mensagem;
	    private String campo;
	    private Object parameter;

	    public GenericoException(String mensagem, String campo, Object parameter) {
	        this.mensagem = mensagem;
	        this.campo = campo;
	        this.parameter = parameter;
	    }

	    public String getMensagem() {
	        return mensagem;
	    }

	    public void setMensagem(String mensagem) {
	        this.mensagem = mensagem;
	    }

	    public String getCampo() {
	        return campo;
	    }

	    public void setCampo(String campo) {
	        this.campo = campo;
	    }

	    public Object getParameter() {
	        return parameter;
	    }

	    public void setParameter(Object parameter) {
	        this.parameter = parameter;
	    }

}
