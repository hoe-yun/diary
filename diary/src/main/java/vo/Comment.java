package vo;

//notice 댓글
public class Comment {
	//fk : notice_no, member_id
	private boolean isSecret; // 값이 true이면 비밀 글(본인과 관리자만 볼 수 있게), false면 일반 글
	private int commentNo;
	private int noticeNo;
	private String memberId;
	private String commentContent;
	private String createdate;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public boolean getIsSecret() {
		return isSecret;
	}
	public void setIsSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Comment [isSecret=" + isSecret + ", commentNo=" + commentNo + ", noticeNo=" + noticeNo + ", memberId="
				+ memberId + ", commentContent=" + commentContent + ", createdate=" + createdate + "]";
	}
	
	
}
