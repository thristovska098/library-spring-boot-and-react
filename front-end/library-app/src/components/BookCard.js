import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Button } from 'react-bootstrap';
import '../App.css';


const BookCard = ({ book, updateChanges }) => {
  const id = book.bookId;
  const title = book.name;
  const sources = book.sources;
  const authors = book.authors;
  const requestBase = 'http://localhost:8080/api/books';
  const deleteBook = (e) => {
    fetch(`${requestBase}/${id}`, {
      method: 'DELETE'
    }).then(res => res.text())
      .then(res => console.log(res));
    updateChanges();

  }
  const authors_names = authors.map((a) => {
    let pom = "";
    if (a.name != null) {
      pom = a.name;

    }
    if (a.lastName != null) {
      pom = pom + " " + a.lastName;
    }
    return pom;
  })

  let url_book;
  if (sources !== undefined && sources[0].url != null) {
    url_book = sources[0].url;
  }

  let imageSource = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbXCpiYKfm11YUjU715AE4xto0XO6fzBiL8Q&usqp=CAU';
  if (sources[0].imgSrc != null) {
    imageSource = sources[0].imgSrc
  }
  const writenBy = authors_names.join(", ");
  return (
    <Card style={{ width: '455px', height: '450px', margin: 'auto', marginTop: '20px' }}>
      <Card.Img variant="top" style={{ height: '200px', width: '200px', display: 'flex', marginLeft: 'auto', marginRight: 'auto', marginTop: '15px' }} src={imageSource} alt='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbXCpiYKfm11YUjU715AE4xto0XO6fzBiL8Q&usqp=CAU' />
      <Card.Body style={{ marginLeft: 'auto', marginRight: 'auto' }}>
        <Card.Title>{title}</Card.Title>
        <Card.Text>
          Written by: {writenBy}
        </Card.Text>
        <Card.Text>
          {url_book !== undefined ? (<a href={url_book}> Go to the book..</a>) : "There is no link for this book."}
        </Card.Text>
        <Button variant="primary" onClick={deleteBook}>Delete the book</Button><br />
      </Card.Body>
    </Card>
  );
}

export default BookCard;