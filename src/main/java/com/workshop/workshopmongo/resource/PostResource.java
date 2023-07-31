package com.workshop.workshopmongo.resource;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService uService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = uService.findById(id);

		return ResponseEntity.ok().body(obj);
	}
}
