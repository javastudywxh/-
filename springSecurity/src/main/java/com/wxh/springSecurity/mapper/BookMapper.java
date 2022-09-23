package com.wxh.springSecurity.mapper;

import com.wxh.springSecurity.entity.Books;
import com.wxh.springSecurity.entity.BorrowDetails;
import com.wxh.springSecurity.entity.BorrowList;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: WXH
 * @Date: 2022/9/11 - 09 - 11 - 14:34
 */
@Mapper
public interface BookMapper {
   @Select("select *from book")
    List<Books> getAllBook();
    @Delete("delete from book where bid=#{bid}")
    void deleteBook(int bid);
    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);

    @Insert("insert into borrow(bid,sid,`time`) values(#{bid},#{sid},NOW())")
    void addBorrow(@Param("bid") int bid,@Param("sid") int sid);
    @Select("select *from borrow")
    List<BorrowList> borrowList();
    @Select("select *from book where bid=#{bid}")
    Books getBookById(@Param("bid") int bid);
    @Select("select *from borrow where sid=#{sid}")
    List<BorrowList> borrowListBySid(int sid);
    @Delete("delete from borrow where bid=#{bid} and sid=#{sid}")
    void deleteBorrow(@Param("bid") int bid,@Param("sid") int sid);

    @Results({
            @Result(id = true,column = "id" ,property = "id"),
            @Result(column = "title" ,property = "book_title"),
            @Result(column = "name" ,property = "user_name"),
            @Result(column = "time" ,property = "time")
    })
    @Select("select *from borrow left join book on book.bid = borrow.bid left " +
            "join student on borrow.sid=student.sid")
   List<BorrowDetails> borrowDetails();

    @Select("select count(*) from book")
   int getBookCount();
   @Select("select count(*) from borrow")
   int getBorrowCount();
}
