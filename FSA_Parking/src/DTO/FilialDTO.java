
package DTO;

public class FilialDTO extends FuncionarioDTO{
    
    private String Campus, Campus2,  telefone;

    public String getCampus2() {
        return Campus2;
    }

    public void setCampus2(String Campus2) {
        this.Campus2 = Campus2;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FilialDTO() {
    }

    
    public String getCampus() {
        return Campus;
    }

    public void setCampus(String Campus) {
        this.Campus = Campus;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
}
