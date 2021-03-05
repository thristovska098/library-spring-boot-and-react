import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Nav, Navbar, Form, FormControl, Button } from 'react-bootstrap';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import BooksList from './BooksList';
import BookForm from './BookForm';
import PublisherForm from './PublisherForm';
import CategoryForm from './CategoryForm';
import AuthorForm from './AuthorForm';
import { useState } from 'react'

const Navigation = () => {
  const base='http://localhost:8080/api/books';
  const [searchState, setSearchState] = useState("http://localhost:8080/api/books");
  const getByName = (e) => {
    e.preventDefault();
    let searchContent = document.getElementById("searchInput").value;
    if (searchContent !== "") {
      document.getElementById("searchInput").value = "";
      setSearchState(base + `/find?name=${searchContent}`);
    }
  }


  return (
    <Router>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand to="/">Digital Library</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/">My library</Nav.Link>
            <Nav.Link href="/insertBook">Insert Book</Nav.Link>
            <Nav.Link href="/insertAuthor">Insert Author</Nav.Link>
            <Nav.Link href="/insertPublisher">Insert Publisher</Nav.Link>
            <Nav.Link href="/insertCategory">Insert Category</Nav.Link>
          </Nav>
          <Form inline>
            <FormControl type="text" placeholder="Search by title.." className="searchBar" id="searchInput" />
            <Button variant="outline-success" type="submit" className="searchButton" style={{ marginLeft: '10px' }} onClick={getByName}>Search Books</Button>
          </Form>
        </Navbar.Collapse>
      </Navbar>
      <Switch>
        <Route exact path="/">
          <BooksList searchName={searchState} ></BooksList>
        </Route>
        <Route exact path="/insertBook" component={BookForm} />
        <Route exact path="/insertPublisher" component={PublisherForm}></Route>
        <Route exact path="/insertAuthor" component={AuthorForm}></Route>
        <Route exact path="/insertCategory" component={CategoryForm}></Route>
      </Switch>

    </Router>

  );
}
export default Navigation;