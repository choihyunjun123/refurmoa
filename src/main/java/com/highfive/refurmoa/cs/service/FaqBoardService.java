package com.highfive.refurmoa.cs.service;

import com.highfive.refurmoa.cs.dto.request.FaqBoardUpdateDTO;
import com.highfive.refurmoa.cs.dto.request.FaqBoardWriteDTO;
import com.highfive.refurmoa.entity.FaqBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

public interface FaqBoardService {
    // Faq 목록 조회
    public Page<FaqBoard> getFaqBoard(int faq_cate, Pageable pageable);

    // Faq 목록 검색(제목)
    public Page<FaqBoard> searchFaqBoard(String search, Pageable pageable);

    // Faq 삭제
    public int deleteFaqBoard(@RequestParam int faq_num);

    // Faq 작성
    public int writeFaqBoard(FaqBoardWriteDTO faqBoardWriteDTO);

    // Faq 수정
    public int updateFaqBoard(FaqBoardUpdateDTO faqBoardUpdateDTO);

}
