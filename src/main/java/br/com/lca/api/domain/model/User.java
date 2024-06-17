package br.com.lca.api.domain.model;

import br.com.lca.api.config.SecutiryRoles;
import br.com.lca.api.controllers.exceptions.EmptyDataTransferObject;
import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserUpdateDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_users")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 80, nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 13)
    private String telephone;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String cnpj;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

    private List<SecutiryRoles> roles = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String telephone, String cpf, String cnpj) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.orders = new ArrayList<>();
        this.roles.add(SecutiryRoles.USER);
    }

    public User(UserCreateDTO createDTO) {
        this.firstName = createDTO.firstName();
        this.lastName = createDTO.lastName();
        this.email = createDTO.email();
        this.password = createDTO.password();
        this.telephone = createDTO.telephone();
        this.orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<SecutiryRoles> getRoles() {
        return roles;
    }

    public void setRoles(SecutiryRoles role) {
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.name())));

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", orders=" + orders +
                '}';
    }

    public void updateData(UserUpdateDTO updateDTO) {
        boolean emptyDTO =
                updateDTO.firstName() == null
                        && updateDTO.lastName() == null
                        && updateDTO.email() == null
                        && updateDTO.password() == null
                        && updateDTO.cpf() == null
                        && updateDTO.cnpj() == null;

        if (emptyDTO) throw new EmptyDataTransferObject();

        this.firstName = updateDTO.firstName() != null && !updateDTO.firstName().isBlank() ? updateDTO.firstName() : getFirstName();
        this.lastName = updateDTO.lastName() != null && !updateDTO.lastName().isBlank() ? updateDTO.lastName() : getLastName();
        this.email = updateDTO.email() != null && !updateDTO.email().isBlank() ? updateDTO.email() : getEmail();
        this.password = updateDTO.password() != null && !updateDTO.password().isBlank() ? updateDTO.password() : getPassword();
        this.telephone = updateDTO.telephone() != null && !updateDTO.telephone().isBlank() ? updateDTO.telephone() : getTelephone();
        this.cpf = updateDTO.cpf() != null && !updateDTO.cpf().isBlank() ? updateDTO.cpf() : getCpf();
        this.cnpj = updateDTO.cnpj() != null && !updateDTO.cnpj().isBlank() ? updateDTO.cnpj() : getCnpj();
    }
}
