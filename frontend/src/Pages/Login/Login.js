import React, { Component } from "react";
import {
  Form,
  Input,
  Button,
  Cascader,
  Divider,
  Row,
  Typography,
  Layout,
  message,
  Modal,
} from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import "antd/dist/antd.css";
import "./Login.css";
import Register from "./Register";
import RegisterFaculty from "./Register_faculty";

const { Header, Content, Footer, Sider } = Layout;
const { Title } = Typography;

const designation = [
  {
    value: "professor",
    label: "Professor",
  },
  {
    value: "student",
    label: "Student",
  },
];

export default class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      register_student: false,
      register_faculty: false,
    }
    this.onCancelRegisterFaculty = this.onCancelRegisterFaculty.bind(this)
    this.onCancelRegisterStudent = this.onCancelRegisterStudent.bind(this)
  }

  login = (r, e) => {
    console.log(r);
    let body = {
      userType: r.designation[0],
      email: r.email,
      password: r.password,
    };
    console.log(JSON.stringify(body));
    let url = sessionStorage.getItem("proxy")+`home/login`;
    console.log(url);
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(body),
    })
      .then((response) => response.json())
      .then((response) => {
        console.log(response);
        if (response.status === "Success") {
          console.log(response);
          sessionStorage.setItem("email", r.email);
          sessionStorage.setItem("designation", r.designation[0]);
          sessionStorage.setItem("logged_in", "true");
          message.success(response.message, 1)
        } else {
          console.log("error", response);
          sessionStorage.setItem("logged_in", "false");
          message.error(response.message, 5);
        }
        if (sessionStorage.getItem("logged_in") === "true") {
          if (sessionStorage.getItem("designation") !== "professor") {
            window.location.replace("/home");
          } else {
            window.location.replace("/home_faculty");
          }
        }
      })
      .catch((err) => console.log(err));
  };

  onCancelRegisterFaculty = () => {
    this.setState({register_faculty: false})
  }

  onCancelRegisterStudent = () => {
    this.setState({register_student: false})
  }


  render() {
    return (
      <Layout style={{ minHeight: "100vh"}}>
        <Content>
          <div>
            <Row justify="center" align="middle">
              <img width={200} src={"../../iiitb.png"} />
            </Row>
            <Row justify="center" align="bottom">
              <Form
                name="normal_login"
                className="login-form"
                initialValues={{ remember: true }}
                onFinish={this.login}
              >
                <Title className="center_title" level={3}>
                  PE/RE Application System
                </Title>
                <Divider plain>Login form</Divider>
                <Form.Item
                  name="email"
                  rules={[
                    { required: true, message: "Please input your Email!" },
                  ]}
                >
                  <Input
                    prefix={<UserOutlined className="site-form-item-icon" />}
                    placeholder="Email"
                  />
                </Form.Item>

                <Form.Item
                  name="password"
                  rules={[
                    { required: true, message: "Please input your Password!" },
                  ]}
                >
                  <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="Password"
                  />
                </Form.Item>

                <Form.Item
                  name="designation"
                  rules={[
                    {
                      type: "array",
                      required: true,
                      message: "Please select your Designation!",
                    },
                  ]}
                >
                  <Cascader options={designation} placeholder="designation" />
                </Form.Item>

                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    className="login-form-button"
                  >
                    Log in
                  </Button>
                </Form.Item>

                <Form.Item>
                  <Button className="login-form-button" onClick={() => {this.setState({register_student: true})}}>
                    Student Register
                  </Button>
                  <Button className="login-form-button" onClick={() => {this.setState({register_faculty: true})}}>
                    Faculty Register
                  </Button>
                </Form.Item>
              </Form>
            </Row>
          </div>
        </Content>
        <Modal
          visible={this.state.register_faculty}
          title="Faculty Registration"
          footer={null}
          style={{width: "100%"}}
        >
          <RegisterFaculty {...this}{...this.state}/>
        </Modal>
        <Modal
          visible={this.state.register_student}
          title="Student Registration"
          footer={null}
        >
          <Register {...this}{...this.state}/>
        </Modal>
        <Footer style={{ textAlign: "center" }}>
          {" "}
          Created by Ashutosh soni - MT2021026 ©2022
        </Footer>
      </Layout>
    );
  }
}
