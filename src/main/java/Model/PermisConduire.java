package Model;

import java.sql.Date;
import java.util.UUID;

public class PermisConduire {

	private int num;
	private Date date;
	private String lieu;
	private String type;
	private String idPermis;
	
	public PermisConduire() {
		super();
	}
	public PermisConduire(int num, Date date, String lieu, String type) {
		super();
		setIdPermis((UUID.randomUUID().toString()));
		this.num = num;
		this.date = date;
		this.lieu = lieu;
		this.type = type;
	}
	
	public PermisConduire(int num, String type, String idPermis) {
		super();
		this.num = num;
		this.type = type;
		this.idPermis = idPermis;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdPermis() {
		return idPermis;
	}
	public void setIdPermis(String idPermis) {
		this.idPermis = idPermis;
	}
	@Override
	public String toString() {
		return "PermisConduire [num=" + num + ", date=" + date + ", lieu=" + lieu + ", type=" + type + "]";
	}
	
}
