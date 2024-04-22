package br.com.livrariamaua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.livrariamaua.domain.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {

	@Query(value = "SELECT * FROM BOOKS b WHERE UPPER(b.title) LIKE %:title%", nativeQuery = true)
	public Books findByTitle(@Param("title") String title);
	
	@Query(value = "SELECT * FROM BOOKS b WHERE UPPER(b.author) LIKE %:author%", nativeQuery = true)
	public Books findByAuthor(@Param("author") String author);
	
	@Query(value = "SELECT * FROM BOOKS b WHERE UPPER(b.gender) LIKE %:gender%", nativeQuery = true)
	public Books findByGender(@Param("gender") String gender);
	
	@Modifying
	@Query(value = "UPDATE BOOKS b SET b.title=:title, b.author=:author, b.quantity=:quantity,"
			+ "b.gender=:gender WHERE b.id=:id", nativeQuery = true)
	public void updateBooks(@Param("title") String title, @Param("author") String author, 
			@Param("quantity") Integer quantity, @Param("gender") String gender, @Param("id") Integer id);
}
