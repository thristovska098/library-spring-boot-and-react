import React from 'react'
import { useEffect, useState } from 'react';
import { Form } from 'react-bootstrap';


const AuthorsList = ({ getAuthor }) => {
    const searchName = "http://localhost:8080/api/authors";
    const [authors, setAuthors] = useState([]);

    const handleSelect = (e) => {
        let p = e.target.value;
        let obj = { ssn: p };
        getAuthor(obj);
    }
    useEffect(() => {
        const loadAuthors = () => {
            fetch(searchName)
                .then(res => res.json())
                .then(res => {
                    setAuthors(res);
                })
                .catch(e => {
                    console.log(e);
                    return e;
                });
        }
        loadAuthors();
    }, [])
    return (
        <Form.Group controlId="exampleForm.SelectCustom">
            <Form.Control as="select" placeholder="something" onChange={handleSelect}>
                <option value="" default >Author</option>
                {
                    authors.map((a) => {
                        let name = "";
                        let lname = "";
                        if (a.name !== undefined) {
                            name = a.name;
                        }
                        if (a.lastName !== undefined) {
                            lname = a.lastName;
                        }
                        let authorInf = "ID:" + a.ssn + " " + name + " " + lname;
                        return (<option key={a.ssn} value={a.ssn}>{authorInf}</option>)
                    })
                }
            </Form.Control>
        </Form.Group>

    );
}
export default AuthorsList;