import React from 'react';
import { Form, Button } from 'react-bootstrap';

const AuthorForm = () => {
    const reqBase = 'http://localhost:8080/api/authors';
    const saveAuthor = (e) => {
        e.preventDefault();
        let ssn = document.getElementById("ssnId").value;
        let name = document.getElementById("nameId").value;
        let lastName = document.getElementById("lastNameId").value;
        let birthDate = document.getElementById("birthdateId").value;

        if (ssn.length !== 13 && ssn !== "") {
            alert("Please enter the SSN again. The SSN must be 13 characters");
        }
        if (birthDate.length !== 10 || birthDate.charAt(4) !== "-" || birthDate.charAt(7) !== "-") {
            alert("Invalid date. The date must be in the following format: YYYY-MM-DD");
        }

        if (name === "") {
            name = null;
        }
        if (lastName === "") {
            lastName = null;
        }
        if (birthDate === "") {
            birthDate = null;
        }
        let obj = { ssn, name, lastName, birthDate };

        document.getElementById("ssnId").value = "";
        document.getElementById("nameId").value = "";
        document.getElementById("lastNameId").value = "";
        document.getElementById("birthdateId").value = "";
        fetch(reqBase,
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(obj)
            })
            .then(() => {
                alert("Author successfully saved. ")
            })
            .catch(e => {
                console.log(e);
                return e;
            });

    }
    return (
        <div id="formCmp" style={{ margin: 'auto', width: '500px', marginTop: '20px' }}>
            <Form>
                <h3 style={{ marginBottom: '30px', color: '#696766' }}>Insert Author</h3>
                <Form.Group>
                    <Form.Label>SSN:</Form.Label>
                    <Form.Control type="text" id="ssnId" />
                </Form.Group>

                <Form.Group>
                    <Form.Label>Name:</Form.Label>
                    <Form.Control id="nameId" />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Last name:</Form.Label>
                    <Form.Control id="lastNameId" />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Birthdate:</Form.Label>
                    <Form.Control id="birthdateId" />
                    <Form.Text className="text-muted">
                        The date must be in the following format YYYY-MM-DD
                    </Form.Text>
                </Form.Group>

                <Button variant="primary" type="submit" onClick={saveAuthor}>
                    Add Author
                </Button>
            </Form>
        </div>

    );
}

export default AuthorForm;