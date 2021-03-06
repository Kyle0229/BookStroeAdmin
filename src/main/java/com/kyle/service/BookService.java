package com.kyle.service;

import com.kyle.domain.*;

import java.util.List;

public interface BookService {

//    public String saveBook(Book book);
//    //根据Bid查询书籍信息
    Book findBookId(Integer bid);
//    //根据aid查询作者信息
    Author findBookAuthor(Integer aid);
//    //降序查询所有的书的scount收藏数
//    List<Book> findScount();
//    //降序查询所有的书的收入
//    List<Book> findNumMoney();
//    //正序查收入
    List<Book> findAscMoney();
//    //根据书的id查询所有的章节
//    List<Chapter> findBookChapter(Integer bid);
//    //根据章节id查出章节内容
//    String findBookContent(Integer chid);
//    //根据分类id查出所有书
//    List<Book> findCatalog(Integer cid);
//    //根据书的cid查出所属分类
    String findBookCatalog(Integer cid);
//
//    List<Book> findBtickets();
//
    List<Book> findBookAll();
//
//    List<Book> findNewBook();
//
//    List<Book> findRomantic();
//
//    List<Book> findMan();
//
//    Paid findCollect(Integer uid, Integer bid);
//
//    BookStore findBookStore(Integer uid, Integer bid);

    List<Book> findNoAudit();
}
