import React from 'react';
import { Form } from 'react-bootstrap';

const SourceForm = ({ getSource }) => {
  const saveSource = (e) => {
    e.preventDefault();

    let url = document.getElementById("formatId").value;
    let format = document.getElementById("urlId").value;
    let imgSrc = document.getElementById("imageUrlId").value;
    if (url === "") {
      url = null;
    }
    if (format === "") {
      format = null;
    }
    if (imgSrc === "") {
      imgSrc = null;
    }

    let obj = { url, format, imgSrc };
    getSource(obj);

  }

  return (
    <div>

      <Form.Group>
        <Form.Label>Format:</Form.Label>
        <Form.Control type="text" id="formatId" onChange={saveSource} />
      </Form.Group>

      <Form.Group>
        <Form.Label>Url:</Form.Label>
        <Form.Control id="urlId" onChange={saveSource} />
      </Form.Group>

      <Form.Group>
        <Form.Label>Image URL:</Form.Label>
        <Form.Control id="imageUrlId" onChange={saveSource} />
      </Form.Group>


    </div>
  );
}

export default SourceForm;