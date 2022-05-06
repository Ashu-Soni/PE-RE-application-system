import React, { Component } from "react";

import {
  List,
  Avatar,
  Space,
  Divider,
  Typography,
  Descriptions,
  Tag,
} from "antd";
import { MessageOutlined, LikeOutlined, StarOutlined } from "@ant-design/icons";
import Layout, { Content, Header } from "antd/lib/layout/layout";
const { Title } = Typography;

export default class StudentDashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      listData: [],
    };
  }

  checkForTag = (value) => {
    let renderTab = null;
    switch (value) {
      case "Pending":
        renderTab = <Tag color="processing">Pending</Tag>;
        break;
      case "Accepted":
        renderTab = <Tag color="success">Accepted</Tag>;
        break;
      case "Rejected":
        renderTab = <Tag color="error">Rejected</Tag>;
        break;
      default:
        renderTab = <Tag color="processing">Pending</Tag>;
        break;
    }
    return renderTab;
  };

  componentDidMount = () => {
    let url = sessionStorage.getItem("proxy")+"dashboard/MyApplications";
    let body = {
      studentemail: sessionStorage.getItem("email"),
    };
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
        this.setState({ listData: response });
      })
      .catch((err) => console.log(err));
  };

  render() {
    const {listData} = this.state;
    return (
      <Layout>
        <Header style={{ backgroundColor: "lightgray", textAlign: "center" }}>
          <h2>Your Applications</h2>
        </Header>
        <Content>
          <List
            itemLayout="vertical"
            size="large"
            pagination={{
              onChange: (page) => {
                console.log(page);
              },
            }}
            dataSource={listData}
            renderItem={(item) => (
              <List.Item key={item.title}>
                <Descriptions title={item.name}>
                  <Descriptions.Item label="Type">
                    {item.type}
                  </Descriptions.Item>
                  <Descriptions.Item label="Professor Name">
                    {item.faculty}
                  </Descriptions.Item>
                  <Descriptions.Item label="Professor Email">
                    {item.facultyemail}
                  </Descriptions.Item>
                  <Descriptions.Item label="Status">
                    {this.checkForTag(item.status)}
                  </Descriptions.Item>
                </Descriptions>
                <Divider></Divider>
              </List.Item>
            )}
          />
        </Content>
      </Layout>
    );
  }
}
