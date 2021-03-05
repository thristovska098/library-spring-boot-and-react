import React from 'react';
import { useEffect, useState } from 'react';
import { Form } from 'react-bootstrap';


const PublishersList = ({ getPublisher }) => {

    const searchName = "http://localhost:8080/api/publishers";
    const [publishers, setPublishers] = useState([]);

    const handleSelect = (e) => {
        let p = e.target.value;
        let obj = { publisherId: p };
        getPublisher(obj);
    }

    useEffect(() => {
        const loadPublishers = () => {
            fetch(searchName)
                .then(res => res.json())
                .then(res => {
                    setPublishers(res);
                })
                .catch(e => {
                    console.log(e);
                    return e;
                });
        }
        loadPublishers();
    }, [])
    return (
        <Form.Group controlId="exampleForm.SelectCustom">

            <Form.Control as="select" custom onChange={handleSelect}>
                <option value="" default >Publisher</option>
                {
                    publishers.map((p) => {
                        let name = "";
                        if (p.name !== undefined) {
                            name = p.name;
                        }
                        let publisherInf = "ID:" + p.publisherId + " " + name;
                        return (<option value={p.publisherId} key={p.publisherId}>{publisherInf}</option>)
                    })
                }
            </Form.Control>
        </Form.Group>

    );
}

export default PublishersList;