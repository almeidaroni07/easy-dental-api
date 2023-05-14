package rw.solution.easy.dental.security.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import rw.solution.easy.dental.model.Customer;


@Entity
@Table(name = "usuario")
public class User implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3974526512869374184L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "nome")
	private String nome;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	@JsonIgnore
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusUsuario status;
	
	@JsonIgnore
	@Lob
	@Column(name = "bl_foto", nullable = true)
	private byte[] foto;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
    private List<Role> roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public StatusUsuario getStatus() {
		return status;
	}
	public void setStatus(StatusUsuario status) {
		this.status = status;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result + Objects.hash(customer, id, nome, password, status, username);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(customer, other.customer) && Arrays.equals(foto, other.foto)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && status == other.status
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", customer=" + customer + ", nome=" + nome + ", username=" + username + ", password="
				+ password + ", status=" + status + ", foto=" + Arrays.toString(foto) + "]";
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
		
		try {
			if("ATIVO".equals(this.getStatus().toString())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
