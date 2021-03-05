import React from 'react'
import { Form, Button } from 'react-bootstrap';

const PublisherForm = () => {

    const reqBase='http://localhost:8080/api/publishers';
    const savePublisher=(e)=>{
        e.preventDefault();
        let name=document.getElementById("nameId").value;
        let address=document.getElementById("addressId").value;
        if(name===""){
            name=null;
        }
        if(address===""){
            address=null;
        }
        let obj={name,address};
        document.getElementById("nameId").value="";
        document.getElementById("addressId").value="";
       
        fetch(reqBase,
            {method:'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)})
        .then(() => {
            alert("Publisher successfully saved. ")})
        .catch(e => {
          console.log(e);
          return e;
        });

    }
    return (
        <div id="formCmp" style={{ margin: 'auto', width: '500px', marginTop: '20px' }}>
            <Form>
                <h3 style={{marginBottom:'30px', color:'#696766'}}>Insert Publisher</h3>
                <Form.Group>
                    <Form.Label>Name:</Form.Label>
                    <Form.Control type="text" id="nameId"/>
                </Form.Group>

                <Form.Group>
                    <Form.Label>Address:</Form.Label>
                    <Form.Control id="addressId"/>
                    <Form.Text className="text-muted">
                        Make sure that you write the address in the following order <br />"Street and number, City, Zip, Country"
                    </Form.Text>
                </Form.Group>

                <Button variant="primary" type="submit" onClick={savePublisher}>
                    Add Publisher
                </Button>
            </Form>
        </div>
    );
}


export default PublisherForm;