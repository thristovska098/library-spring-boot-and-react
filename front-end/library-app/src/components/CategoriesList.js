import React from 'react';
import { useEffect, useState } from 'react';
import { Form } from 'react-bootstrap';


const CategoriesList = ({ getCategory }) => {

    const searchName = "http://localhost:8080/api/categories";
    const [categories, setCategories] = useState([]);

    const handleSelect = (e) => {
        let p = e.target.value;
        let obj = { categoryId: p };
        getCategory(obj);
    }

    useEffect(() => {
        const loadCategories = () => {
            fetch(searchName)
                .then(res => res.json())
                .then(res => {
                    setCategories(res);
                })
                .catch(e => {
                    console.log(e);
                    return e;
                });
        }
        loadCategories();
    }, [])
    return (
        <Form.Group controlId="exampleForm.SelectCustom">
            <Form.Control as="select" custom onChange={handleSelect}>
                <option value="" default>Category</option>

                {
                    categories.map((c) => {
                        let name = "";
                        if (c.name !== undefined) {
                            name = c.name;
                        }
                        let categoryInf = "ID:" + c.categoryId + " " + name;
                        return (<option key={c.categoryId} value={c.categoryId}>{categoryInf}</option>)
                    })
                }
            </Form.Control>
        </Form.Group>
    );
}

export default CategoriesList;