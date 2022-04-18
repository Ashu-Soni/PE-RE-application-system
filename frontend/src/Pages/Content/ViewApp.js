import React, { Component } from "react";
import { Button, Input, Form } from "antd";

export default class ViewApp extends Component {
  componentDidMount = () => {
    this.props.editModalForm();
  };

  componentDidUpdate = () => {
    this.props.editModalForm();
  };

  accept_application = (values, e) => {
    console.log(values);
    // let url = `http://localhost:9090/accept_application`;
    // fetch(url, {
    //   method: "POST",
    //   headers: {
    //     "Content-Type": "application/json",
    //     "Access-Control-Allow-Origin": "*",
    //   },
    //   body: JSON.stringify(values),
    // })
    //   .then((res) => res.json())
    //   .then((response) => {
    //     if (response.status) {
    //       console.log("Registration success!");
    //       message.success("Registration success!", 1);
    //       window.location.replace("/login");
    //     } else {
    //       console.log("Registration failure!");
    //       message.success(response.message, 5);
    //     }
    //   });
  };

  render() {
    return (
      <Form
        layout="vertical"
        ref={this.props.editFormRef}
        onFinish={this.accept_application}
      >
        <Form.Item name="name" label="Name">
          <Input />
        </Form.Item>

        <Form.Item name="type" label="Type">
          <Input />
        </Form.Item>

        <Form.Item
          name="skills"
          label="Skills"
          rules={[
            {
              required: false,
              message: "Enter your skills",
            },
          ]}
        >
          <Input style={{ height: "150px" }} />
        </Form.Item>

        <Form.Item name="experience" label="Experience">
          <Input style={{ height: "150px" }} />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Accept
          </Button>
        </Form.Item>
        <Form.Item>
          <Button>Reject</Button>
        </Form.Item>
        <Form.Item>
          <Button onClick={this.props.onCancelView}>Cancel</Button>
        </Form.Item>
      </Form>
    );
  }
}
