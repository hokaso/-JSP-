package Pet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="video1")
public class Vedio1 {
	private int id;//��Ƶid
	private String address;//��Ƶ��ŵ�ַ
	private Member member;
	@Id
	@GenericGenerator(name = "generater",strategy = "native")
	@GeneratedValue(generator = "generater")
	@Column(name = "id",length=11)
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String fileFileName) {
		this.address = fileFileName;
	}
	public void setId(int id) {
		this.id = id;
	}
    private String day;
	
	private String month;
	
	private String year;
	@Transient
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Transient
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Transient
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
    private String vedioTitle;
  
	@Column(name = "vedioAuthor", length=20)
	public String getVedioAuthor() {
		return vedioAuthor;
	}
	
	public void setVedioAuthor(String vedioAuthor) {
		this.vedioAuthor = vedioAuthor;
	}
	@ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "member1_ID_FK"))
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Column(name = "vedioDate")
	public java.util.Date getVedioDate() {
		return vedioDate;
	}
	public void setVedioDate(java.util.Date vedioDate) {
		this.vedioDate = vedioDate;
	}
	private String vedioContent;
    
    private String vedioAuthor;
    private java.util.Date vedioDate;

	
	@Column(name = "vedioTitle", length=255)
	public String getVedioTitle() {
		return vedioTitle;
	}
	public void setVedioTitle(String vedioTitle) {
		this.vedioTitle = vedioTitle;
	}
	@Column(name = "vedioContent", length=255)
	public String getVedioContent() {
		return vedioContent;
	}
	public void setVedioContent(String vedioContent) {
		this.vedioContent = vedioContent;
	}
	
}
