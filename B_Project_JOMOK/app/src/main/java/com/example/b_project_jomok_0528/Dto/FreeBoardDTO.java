package com.example.b_project_jomok_0528.Dto;

import java.io.Serializable;

public class FreeBoardDTO implements Serializable {
    public int Board_num;
    public String Board_id, Board_subject, Board_content, Board_file;
    public int Board_re_ref, Board_re_lev, Board_re_seq, Board_readcount;
    public String Board_date;
    /*public Date Board_date;*/

    public FreeBoardDTO(int board_num, String board_id, String board_subject, String board_content, String board_file, int board_re_ref, int board_re_lev, int board_re_seq, int board_readcount, String board_date) {
        this.Board_num = board_num;
        this.Board_id = board_id;
        this.Board_subject = board_subject;
        this.Board_content = board_content;
        this.Board_file = board_file;
        this.Board_re_ref = board_re_ref;
        this.Board_re_lev = board_re_lev;
        this.Board_re_seq = board_re_seq;
        this.Board_readcount = board_readcount;
        this.Board_date = board_date;
    }

    public int getBoard_num() {
        return Board_num;
    }

    public void setBoard_num(int board_num) {
        Board_num = board_num;
    }

    public String getBoard_id() {
        return Board_id;
    }

    public void setBoard_id(String board_id) {
        Board_id = board_id;
    }

    public String getBoard_subject() {
        return Board_subject;
    }

    public void setBoard_subject(String board_subject) {
        Board_subject = board_subject;
    }

    public String getBoard_content() {
        return Board_content;
    }

    public void setBoard_content(String board_content) {
        Board_content = board_content;
    }

    public String getBoard_file() {
        return Board_file;
    }

    public void setBoard_file(String board_file) {
        Board_file = board_file;
    }

    public int getBoard_re_ref() {
        return Board_re_ref;
    }

    public void setBoard_re_ref(int board_re_ref) {
        Board_re_ref = board_re_ref;
    }

    public int getBoard_re_lev() {
        return Board_re_lev;
    }

    public void setBoard_re_lev(int board_re_lev) {
        Board_re_lev = board_re_lev;
    }

    public int getBoard_re_seq() {
        return Board_re_seq;
    }

    public void setBoard_re_seq(int board_re_seq) {
        Board_re_seq = board_re_seq;
    }

    public int getBoard_readcount() {
        return Board_readcount;
    }

    public void setBoard_readcount(int board_readcount) {
        Board_readcount = board_readcount;
    }

    public String getBoard_date() {
        return Board_date;
    }

    public void setBoard_date(String board_date) {
        Board_date = board_date;
    }
}