import React from 'react';
import { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import AuthorsList from './AuthorsList';
import SourceForm from './SourceForm';
import PublishersList from './PublishersList';
import CategoriesList from './CategoriesList';

const BookForm = () => {
  const reqBase = 'http://localhost:8080/api/books';
  const [publisher, setPublisher] = useState();
  const [category, setCategory] = useState();
  const [author, setAuthor] = useState();
  const [source, setSource] = useState();

  const getPublisher = (p) => {
    setPublisher(p);
  }
  const getCategory = (c) => {
    setCategory(c);
  }
  const getAuthor = (a) => {
    setAuthor(a);
  }
  const getSource = (s) => {
    setSource(s);
  }
  const saveBook = (ev) => {
    ev.preventDefault();
    let t = document.getElementById("titleId").value;
    let e = document.getElementById("editionId").value;
    let d = document.getElementById("descriptionId").value;
    if (t === "") {
      t = null;
    }
    if (e === "") {
      e = null;
    }
    if (d === "") {
      d = null;
    }
    let obj = {};
    obj.name = t;
    obj.edition = e;
    obj.description = d;
    obj.sources = [source];
    obj.publisher = publisher;
    obj.authors = [author];
    obj.categories = [category];
    if (category === undefined) {
      alert("Explicitely select category!");
      console.log(obj.authors[0].author);
    } else if (author === undefined) {
      alert("Explicitely select author!");
    }
    else if (publisher === undefined) {
      alert("Explicitely select publisher!");
    } else {
      console.log(obj);

      fetch(reqBase,
        {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(obj)
        })
        .then(() => {
          alert("Book successfully saved. ")
        })
        .catch(e => {
          console.log(e);
          return e;
        });
    }
    document.getElementById("titleId").value = "";
    document.getElementById("editionId").value = "";
    document.getElementById("descriptionId").value = "";
    document.getElementById("formatId").value = "";
    document.getElementById("urlId").value = "";
    document.getElementById("imageUrlId").value = "";
  }
  return (
    <div id="formCmp" style={{ margin: 'auto', width: '500px', marginTop: '20px' }}>
      <Form>
        <Form.Group>
          <h3 style={{ marginBottom: '10px', color: '#696766' }}>Information for the book:</h3>
          <Form.Label>Title:</Form.Label>
          <Form.Control type="text" id="titleId" />
        </Form.Group>

        <Form.Group>
          <Form.Label>Edition:</Form.Label>
          <Form.Control id="editionId" />
        </Form.Group>
        <Form.Group>
          <Form.Label>Description:</Form.Label>
          <Form.Control id="descriptionId" />
        </Form.Group>
        <h3 style={{ marginBottom: '10px', color: '#696766' }}>Information for the source:</h3>
        <SourceForm getSource={getSource.bind()}></SourceForm><br></br>
        <AuthorsList getAuthor={getAuthor.bind()}></AuthorsList><br />
        <PublishersList getPublisher={getPublisher.bind()}></PublishersList><br />
        <CategoriesList getCategory={getCategory.bind()}></CategoriesList>
        <Button variant="primary" type="submit" style={{ marginTop: '20px', marginBottom: '20px' }} onClick={saveBook}>
          Add Book
        </Button>
      </Form>
    </div>

  );
}

export default BookForm;