package com.workshop.workshopmongo.config;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.AuthorDTO;
import com.workshop.workshopmongo.dto.CommentDTO;
import com.workshop.workshopmongo.repository.PostRepository;
import com.workshop.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private UserRepository uRepository;

    @Autowired
    private PostRepository pRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        uRepository.deleteAll();
        pRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        uRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo.", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("27/01/2020"), "Estou animado!", "Vou viajar para Curitiba e irei visitar tudo da cidade.", new AuthorDTO(alex));

        CommentDTO c1 = new CommentDTO("Vai na fé", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Espero que se divirta", sdf.parse("25/03/2018"), new AuthorDTO(maria));
        CommentDTO c3 = new CommentDTO("Não gostei muito do lugar que você vai", sdf.parse("29/01/2020"), new AuthorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        pRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1));
        alex.getPosts().addAll(Arrays.asList(post2));
        uRepository.saveAll(Arrays.asList(maria, alex));
    }
}
