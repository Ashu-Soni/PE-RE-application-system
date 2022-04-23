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

export default class RegisterFaculty extends Component {
  register = (values, e) => {
    console.log(values);

    let body = {
      email: values.email,
      name: values.name,
      password: values.password,
      office: values.cabin,
      designation: values.designation,
      phone: values.contact,
      research: values.research_interest,
      website: values.website,
    };

    console.log(JSON.stringify(body));
    let url = this.props.state.url+"home/register_faculty";
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
        if (response.status) {
          console.log("Registration success!");
          message.success("Registration success!", 1);
          this.props.onCancelRegisterFaculty();
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
                  name="designation"
                  label="Designation"                  
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="cabin"
                  label="Cabin No."                  
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="research_interest"
                  label="Research"                  
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="contact"
                  label="Contact No."
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  name="website"
                  label="Website"                  
                >
                  <Input type="url" />
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                  <Button type="primary" htmlType="submit">
                    Register
                  </Button>
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                  <Button onClick={this.props.onCancelRegisterFaculty}>Cancel</Button>
                </Form.Item>
              </Form>
            </Row>
          </div>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          {" "}
          Created by Ashutosh soni - MT2021026 ©2022
        </Footer>
      </Layout>
    );
  }
}
