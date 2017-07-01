package warehouse;

public class Fact {
	private int _articleId;
	private int _unitsSold;
	
	public void setArticleId(int id) {
		_articleId = id;
	}
	
	public void setUnitsSold(int units) {
		_unitsSold = units;
	}
	
	public int getArticleId() {
		return _articleId;
	}
	
	public int getUnitsSold() {
		return _unitsSold;
	}
}
