import React, { Component } from "react";
import { Button, Input, Form, message, Cascader } from "antd";

const types = [
  {
    value: "project_elective",
    label: "Project Elective",
  },
  {
    value: "research_elective",
    label: "Research Elective",
  },
];

export default class EditElective extends Component {
  componentDidMount = () => {
    this.props.editModalForm();
  };

  edit_elective = (r, e) => {
    console.log(r);
    let url = sessionStorage.getItem("proxy")+`dashboard/MyElectives/Update`
    let body = {
      eid: r.eid,
      name: r.project_name,
      description: r.description,
      vacancy: r.slots,
      email: sessionStorage.getItem("email"),
      type: r.type[0],  
    };
    console.log(JSON.stringify(body))
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(body)
    }).then(res => res.json())
        .then(response => {
          console.log(response);
          if(response.status === "Success") {
            this.props.onCancelEdit();
            message.success(response.message, 0)
          } else {
            this.props.onCancelEdit();
            message.error(response.message, 5)
          }
        }).catch((err) => console.log(err));
  };

  render() {
    return (
      <Form
        layout="vertical"
        onFinish={this.edit_elective}
        ref={this.props.editFormRef}
      >
        <Form.Item
          name="eid"
          label="Elective ID"
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="project_name"
          label="Project Name"
          rules={[
            {
              required: true,
              message: "Project Name is required!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="type"
          label="Type"
          rules={[
            {
              type: "array",
              required: true,
              message: "Elective type is required!",
            },
          ]}
        >
          <Cascader options={types} />
        </Form.Item>
        <Form.Item
          name="description"
          label="Description"
          rules={[
            {
              type: "string",
              required: false,
              message: "Description of the elective",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="slots"
          label="Slots"
          rules={[
            {
              required: true,
              message: "Total number available slots",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
        <Form.Item>
          <Button onClick={this.props.onCancelEdit}>Cancel</Button>
        </Form.Item>
      </Form>
    );
  }
}
