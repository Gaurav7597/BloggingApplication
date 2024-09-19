package org.BloggingApplication.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private  int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;



}
