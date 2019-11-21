package com.kyle.controller;

import com.kyle.Request.BookDown;
import com.kyle.domain.Author;
import com.kyle.domain.Book;
import com.kyle.domain.Catalog;
import com.kyle.domain.Selection;
import com.kyle.mapper.BookRepository;
import com.kyle.mapper.SelectionRepository;
import com.kyle.service.BookService;
import com.kyle.service.SelectionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Resource
    private BookService bookService;
    @Resource
    private BookRepository bookRepository;
    @Resource
    private SelectionRepository selectionRepository;
    @Resource
    private SelectionService selectionService;
    //后台查询
    @RequestMapping("/findBookDown")
    public List<BookDown> findBookDown(){
        List<BookDown> bookDownList=new ArrayList<>();
        List<Book> all = bookService.findAscMoney();
        for (Book list:all){
            Integer bid = list.getBid();
            Integer aid = list.getAid();
            Integer cid = list.getCid();
            String bname = list.getBname();
            Integer btickets = list.getBtickets();
            BigDecimal nummoney = list.getNummoney();
            BigDecimal bprice = list.getBprice();

            Author bookAuthor = bookService.findBookAuthor(aid);
            String aname = bookAuthor.getAname();
            String catalog = bookService.findBookCatalog(cid);
            BookDown bookDown = new BookDown();
            Book book=new Book();
            Author author=new Author();
            Catalog catalog1=new Catalog();
            book.setBid(bid);
            book.setBprice(bprice);
            book.setNummoney(nummoney);
            book.setBname(bname);
            book.setBtickets(btickets);
            bookDown.setBook(book);
//            bookDown.getBook().setBname(bname);
//
//            bookDown.getBook().setNummoney(nummoney);
//            bookDown.getBook().setBtickets(btickets);
//            bookDown.getBook().setBprice(bprice);
            author.setAname(aname);
            catalog1.setCname(catalog);

//            bookDown.getAuthor().setAname(aname);
//            bookDown.getCatalog().setCname(catalog);
            bookDown.setAuthor(author);
            bookDown.setCatalog(catalog1);
            bookDownList.add(bookDown);

        }
        return bookDownList;
    }

    //下架书籍
    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book){
        Integer bid = book.getBid();
        bookRepository.deleteById(bid);
        return "下架成功";
    }
    //查询所有书
    @RequestMapping("/findBookAll")
    public List<Book> findBookAll(){
        List<Book> bookAll = bookService.findBookAll();
        return bookAll;
    }

    //添加推荐
    @RequestMapping(value = "/addSelection",method = RequestMethod.POST)
    public String addSelection(@RequestBody Book book){
        Integer bid = book.getBid();
        System.out.println(bid);
        Optional<Selection> byId = selectionRepository.findByBid(bid);
        System.out.println(byId.isPresent());
        if (byId.isPresent()){
            return "error";
        }
        Selection selection=new Selection();
        selection.setBid(bid);
        selectionRepository.save(selection);
        return "success";
    }
    //查询推荐
    @RequestMapping("/findSelection")
    public List<Book> findSelection(){
        List<Book> bookList=new ArrayList<>();
        List<Selection> selectionId = selectionService.findSelectionId();
//        System.out.println(selectionId);
        for (Selection s:selectionId){
            Integer bid = s.getBid();
            Book bookId = bookService.findBookId(bid);
            bookList.add(bookId);
        }
        return bookList;
    }

    //取消推荐书籍
    @RequestMapping("/deleteSelection")
    public String deleteSelection(@RequestBody Book book){
        Integer bid = book.getBid();
        Selection sid = selectionService.findSid(bid);
        Integer sid1 = sid.getSid();
        selectionRepository.deleteById(sid1);
        return "成功取消";
    }

    @RequestMapping("/findNoAudit")
    public List<BookDown> findNoAudit(){
        List<BookDown> bookDownList=new ArrayList<>();
        List<Book> all = bookService.findNoAudit();
        for (Book list:all){
            Integer bid = list.getBid();
            Integer aid = list.getAid();
            Integer cid = list.getCid();
            String bname = list.getBname();
            BigDecimal bprice = list.getBprice();
            String bpic = list.getBpic();
            Author bookAuthor = bookService.findBookAuthor(aid);
            String aname = bookAuthor.getAname();
            String aphone = bookAuthor.getAphone();
            String catalog = bookService.findBookCatalog(cid);
            BookDown bookDown = new BookDown();
            Book book=new Book();
            Author author=new Author();
            Catalog catalog1=new Catalog();
            book.setBid(bid);
            book.setBpic(bpic);
            book.setBprice(bprice);
            book.setBname(bname);
            bookDown.setBook(book);
            author.setAname(aname);
            author.setAphone(aphone);
            catalog1.setCname(catalog);
            bookDown.setAuthor(author);
            bookDown.setCatalog(catalog1);
            bookDownList.add(bookDown);

        }
        return bookDownList;
    }

    @RequestMapping("/pass")
    public String pass(@RequestBody Book book){
        Integer bid = book.getBid();
        Optional<Book> byId = bookRepository.findById(bid);
        if (byId!=null){

            byId.get().setBstatus(1);
            bookRepository.saveAndFlush(byId.get());
            return "通过";
        }
        return "不通过";
    }
}
