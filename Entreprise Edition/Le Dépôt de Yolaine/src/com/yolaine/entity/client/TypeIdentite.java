package com.yolaine.entity.client;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.yolaine.entity.catalogue.Marque;

@Entity
@Table(name="t_type_identite")
public class TypeIdentite implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;

	@Column(name = "type_identite")
	private String typeIdentite ;

	@OneToMany(mappedBy = "typeidentite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("nom ASC")
	private List<Client> clients;

	public TypeIdentite() {}

	public TypeIdentite(String typeIdentite) {
		this.typeIdentite = typeIdentite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeIdentite() {
		return typeIdentite;
	}

	public void setTypeIdentite(String typeIdentite) {
		this.typeIdentite = typeIdentite;
	}	

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	// ======================================
	// =           Methodes Privées         =
	// ======================================

	// ======================================
	// =   Methodes hash, equals, toString  =
	// ======================================
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TypeIdentite typeidentite = (TypeIdentite) o;

		if (!typeidentite.equals(typeidentite.typeIdentite)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = typeIdentite.hashCode();
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TypeIdentite");
		sb.append("{typeIdentite='").append(typeIdentite).append('\'');
		//sb.append(",clients=").append(clients == null ? 0 : clients.size());
		sb.append('}');
		return sb.toString();
	}

}
