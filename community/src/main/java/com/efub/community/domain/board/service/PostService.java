package com.efub.community.domain.board.service;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.board.dto.PostRequestDto;
import com.efub.community.domain.board.repository.PostRepository;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service//서비스 레이어, 내부에서 자바 로직을 처리함
@Transactional
@RequiredArgsConstructor //final 키워드가 붙은 필드에 대해 생성자 자동 생성
public class PostService {
	private final PostRepository postRepository;
	private final MemberService memberService; //서비스 안에서 다른 서비스 호출 가능



	public Long create(PostRequestDto requestDto) {
		Member member = memberService.findById(requestDto.getMemberId());
		Post board = postRepository.save(requestDto.toEntity(member));
		return board.getPostId();
	}


	public void update(Long postId, PostRequestDto requestDto) {
		Post board = findById(postId);
		board.updatePost(requestDto.getContent());
	}

	public void delete(Long boardId) {
		Post board = findById(boardId);
		postRepository.delete(board);
	}

	@Transactional(readOnly = true)
	public Post findById(Long postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
	}
	@Transactional(readOnly = true)
	public List<Post> findAllDesc() {
		return postRepository.findAllByOrderByPostIdDesc();
	}


	@Transactional(readOnly = true)
	public List<Post> findByWriter(Long accountId) {
		Member writer = memberService.findById(accountId);
		return postRepository.findByWriter(writer);
	}
	@Transactional(readOnly = true)
	public List<Post> findPostsByBoard(Board board)
	{
		return postRepository.findAllByBoard(board);
	}



}
