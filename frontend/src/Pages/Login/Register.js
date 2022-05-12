import react, { Component } from "react";
import {
  Form,
  Input,
  Cascader,
  Row,
  Button,
  Divider,
  Layout,
  message,
} from "antd";

const { Header, Content, Footer, Sider } = Layout;

const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 8 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 16 },
  },
};

const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 8,
    },
  },
};

const program = [
  {
    value: "mtech",
    label: "MTech",
  },
  {
    value: "i_mtech",
    label: "IMTech",
  },
];

const branch = [
  {
    value: "cse",
    label: "CSE",
  },
  {
    value: "ece",
    label: "ECE",
  },
];

export default class Register extends Component {
  register = (values, e) => {
    console.log(values);
    let body = {
      email: values.email,
      name: values.name,
      password: values.password,
      rollno: values.roll_no,
      degree: values.program[0],
      mobile: values.contact,
      branch: values.branch[0],
    };
    console.log(JSON.stringify(body));
    let url = sessionStorage.getItem("proxy")+"home/register_student";
    console.log(url)
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
          console.log("Registration success!");
          message.success(response.message, 1);
          this.props.onCancelRegisterStudent();
        } else {
          console.log("Registration failure!");
          message.success(response.message, 5);
        }
      });
  };

  render() {
    return (
      <Layout style={{ minHeight: "100vh" }}>
        <Content>
          <div>
            <Row justify="center" align="middle">
              <Form
                {...formItemLayout}
                name="register"
                scrollToFirstError
                onFinish={this.register}
              >
                <Divider plain>Registration form</Divider>
                <Form.Item
                  name="email"
                  label="E-mail"
                  rules={[
                    {
                      type: "email",
                      message: "The input is not valid E-mail!",
                    },
                    {
                      required: true,
                      message: "Please input your E-mail!",
                    },
                  ]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="name"
                  label="Name"
                  rules={[
                    {
                      required: true,
                      message: "Please input your name!",
                    },
                  ]}
                  hasFeedback
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="password"
                  label="Password"
                  rules={[
                    {
                      required: true,
                      message: "Please input your password!",
                    },
                  ]}
                  hasFeedback
                >
                  <Input.Password />
                </Form.Item>

                <Form.Item
                  name="confirm"
                  label="Confirm"
                  dependencies={["password"]}
                  hasFeedback
                  rules={[
                    {
                      required: true,
                      message: "Please confirm your password!",
                    },
                    ({ getFieldValue }) => ({
                      validator(_, value) {
                        if (!value || getFieldValue("password") === value) {
                          return Promise.resolve();
                        }
                        return Promise.reject(
                          new Error(
                            "The two passwords that you entered do not match!"
                          )
                        );
                      },
                    }),
                  ]}
                >
                  <Input.Password />
                </Form.Item>

                <Form.Item
                  name="roll_no"
                  label="Roll No."
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="branch"
                  label="Branch"
                >
                  <Cascader options={branch} />
                </Form.Item>

                <Form.Item
                  name="program"
                  label="Program"
                >
                  <Cascader options={program} />
                </Form.Item>

                <Form.Item
                  name="contact"
                  label="Contact No."
                >
                  <Input />
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                  <Button type="primary" htmlType="submit">
                    Register
                  </Button>
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                  <Button onClick={this.props.onCancelRegisterStudent}>Cancel</Button>
                </Form.Item>
              </Form>
            </Row>
          </div>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          {" "}
          Created by Ashutosh soni(MT2021026) Meet Goswami(MT2021075) Shivani Sheth(MT2021126) ©2022
        </Footer>
      </Layout>
    );
  }
}
