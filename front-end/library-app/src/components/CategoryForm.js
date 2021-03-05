import React from 'react';
import { Form, Button } from 'react-bootstrap';

const CategoryForm = () => {
    const reqBase = 'http://localhost:8080/api/categories';
    const saveCategory = (e) => {
        e.preventDefault();
        let name = document.getElementById("nameId").value;
        if (name !== "") {
            const obj = { name };
            fetch(reqBase,
                {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(obj)
                })
                .then(() => {
                    document.getElementById("nameId").value = "";
                    alert("Category successfully saved. ")
                })

                .catch(e => {
                    console.log(e);
                    return e;
                });
        }


    }
    return (
        <div id="formCmp" style={{ margin: 'auto', width: '500px', marginTop: '20px' }}>
            <Form>
                <h3 style={{ marginBottom: '30px', color: '#696766' }}>Insert Category</h3>
                <Form.Group>
                    <Form.Label>Category name:</Form.Label>
                    <Form.Control type="text" id="nameId" />
                </Form.Group>
                <Button variant="primary" type="submit" onClick={saveCategory}>
                    Add Category
                </Button>
            </Form>
        </div>
    );
}

export default CategoryForm;