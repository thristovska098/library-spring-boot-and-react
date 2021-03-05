import React, { useEffect, useState } from 'react';
import BookCard from './BookCard';
import 'bootstrap/dist/css/bootstrap.min.css';

const BooksList = ({ searchName }) => {
  const [books, setBooks] = useState([]);
  const [changed, setChanged] = useState("false");

  const updateChanges = () => {
    setChanged("true");
  }

  useEffect(() => {
    const makeRequest = () => {
      fetch(searchName)
        .then(res => res.json())
        .then(res => {
          setBooks(res);
          setChanged("false");
        })
        .catch(e => {
          console.log(e);
          return e;
        });
    };
    makeRequest();
  }, [searchName, changed]);


  return (
    <div>

      {books.map((b) => {
        return (<BookCard key={b.bookId} book={b} updateChanges={updateChanges.bind()} style={{ float: "right" }}>{b.name}</BookCard>)
      })}

    </div>
  );
}

export default BooksList