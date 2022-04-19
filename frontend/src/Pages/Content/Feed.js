import React, { Component } from "react";
import { List, Avatar, Space, Divider, Typography } from "antd";
import { MessageOutlined, LikeOutlined, StarOutlined } from "@ant-design/icons";
import Layout, { Content, Header } from "antd/lib/layout/layout";
const { Title } = Typography;

const listData = [];
for (let i = 0; i < 23; i++) {
  listData.push({
    href: "https://ant.design",
    title: `ant design part ${i}`,
    avatar: "https://joeschmoe.io/api/v1/random",
    description:
      "Ant Design, a design language for background applications, is refined by Ant UED Team.",
    content:
      "We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.",
  });
}

const IconText = ({ icon, text }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
);

export default class Feed extends Component {
  render() {
    return (
      <Layout>
        <Header style={{ backgroundColor: "lightgray", textAlign: "center" }}>
          <h2>Feeds</h2>
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
                <List.Item.Meta
                  avatar={<Avatar src={item.avatar} />}
                  title={<a href={item.href}>{item.title}</a>}
                  description={item.description}
                />
                {item.content}
                <Divider></Divider>
              </List.Item>
            )}
          />
        </Content>
      </Layout>
    );
  }
}
