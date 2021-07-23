package com.qcby.lxt.vueueditor.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Ueditor
 * @Description TODO
 * @Author lxt
 * @Date 2021/7/22 22:19
 */
@Data
public class UeditorOnlineImage {
    private String state;
    private List<UeditorImage> list = new ArrayList<>();
    private int total;
}
