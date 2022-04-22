import React, { Component } from "react";
import { Button, Input, Form, message } from "antd";

export default class ViewApp extends Component {
  componentDidMount = () => {
    this.props.editModalForm();
  };

  componentDidUpdate = () => {
    this.props.editModalForm();
  };

  accept = (r, e) => {
    let url = `http://localhost:9090/dashboard/Applications/Accept`;
    let body = {
      "aid": r.aid,
      "eid": r.eid,
    }
    console.log(url, JSON.stringify(body))
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(body),
    })
      .then((res) => res.json())
      .then((response) => {
        if (response.status === "Success") {
          console.log("Operation success!");
          message.success("Operation success!", 1);
        } else {
          console.log("Operation failure!");
          message.error(response.message, 5);
        }
      }).catch((err) => console.log(err));
  };

  reject = (r, e) => {
    let url = `http://localhost:9090/dashboard/Applications/Reject`;
    let body = {
      "aid": r.aid,
      "eid": r.eid,
    }
    console.log(url, JSON.stringify(body))
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(body),
    })
        .then((res) => res.json())
        .then((response) => {
          if (response.status === "Success") {
            console.log("Operation success!");
            message.success("Operation success!", 1);
          } else {
            console.log("Operation failure!");
            message.error(response.message, 5);
          }
        }).catch((err) => console.log(err));
  }

  render() {
    return (
      <Form
        layout="vertical"
        ref={this.props.editFormRef}
        onFinish={this.accept}
        onReset={this.reject}
      >
        <Form.Item name="aid" label="Application ID">
          {/*<Input />*/}
        </Form.Item>

        <Form.Item name="eid" label="Elective ID">
          {/*<Input />*/}
        </Form.Item>

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
          <Button type="primary" htmlType={"submit"}>
            Accept
          </Button>
        </Form.Item>
        <Form.Item>
          <Button htmlType={"reset"}>Reject</Button>
        </Form.Item>
        <Form.Item>
          <Button onClick={this.props.onCancelView}>Cancel</Button>
        </Form.Item>
      </Form>
    );
  }
}
