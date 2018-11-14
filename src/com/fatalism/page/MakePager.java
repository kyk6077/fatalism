package com.fatalism.page;

public class MakePager {
	private int curPage;
	private int perPage;
	private RowNumber rowNumber;
	private Search search;
	
	public MakePager(int curPage, Search search) {
		this.curPage = curPage;
		this.perPage = 15;
		this.search = new Search();
		this.search.setSearch(search.getSearch());
		this.search.setKind(search.getKind());
	}
	
	public MakePager(int curPage, int perPage, Search search) {
		this.curPage = curPage;
		this.perPage = perPage;
		this.search = new Search();
		this.search.setSearch(search.getSearch());
		this.search.setKind(search.getKind());
		
	}
	
	public RowNumber MakeRow() {
		rowNumber = new RowNumber();
		rowNumber.setSearch(this.search);
		rowNumber.setStartRow((this.curPage-1)*this.perPage+1);
		rowNumber.setLastRow(this.curPage*this.perPage);
		return rowNumber;
	}
	
	public Pager MakePage(int totalCount) {
		//1. totalpage
		int totalPage = totalCount/this.perPage;
		if(totalCount%this.perPage>0) {
			totalPage += 1;
		}
		//2. totalblock
		int perBlock = 3;
		int totalBlock = totalPage/perBlock;
		if(totalPage%perBlock>0) {
			totalBlock += 1;
		}
		//3. curblock
		int curBlock = this.curPage/perBlock;
		if(this.curPage%perBlock>0) {
			curBlock += 1;
		}
		//4. startNum lastNum
		int startNum = (curBlock-1)*perBlock+1;
		int lastNum = curBlock*perBlock;
		//5. 마지막블럭일때
		if(curBlock == totalBlock) {
			lastNum = totalPage;
		}
		Pager pager = new Pager();
		pager.setCurBlock(curBlock);
		pager.setLastNum(lastNum);
		pager.setSearch(this.search);
		pager.setStartNum(startNum);
		pager.setTotalBlock(totalBlock);
		pager.setTotalPage(totalPage);
		
		return pager;
	}
	
}
