import React, { Component } from "react";
import { Layout, Menu, Breadcrumb, Col, Row, Typography } from "antd";
import {
  TeamOutlined,
  HomeOutlined,
  ProjectOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import Feed from "../Content/Feed";
import Application from "../Content/Application";
import Manage from "../Content/Manage";

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;
const { Title } = Typography;

export default class Home extends Component {
  state = {
    collapsed: false,
    selected_tab: "",
  };

  onCollapse = (collapsed) => {
    console.log(collapsed);
    this.setState({ collapsed });
  };

  logout = () => {
    sessionStorage.setItem("logged_in", false);
    window.location.replace("/login");
  };

  onTabChange = (e) => {
    this.setState({
      selected_tab: e.key,
    });
  };

  render() {
    let renderTab = <Feed />;
    const { selected_tab, collapsed } = this.state;
    switch (selected_tab) {
      case "home":
        renderTab = <Feed />;
        break;
      case "application":
        renderTab = <Application {...this}></Application>;
        break;
      case "manage_app":
        renderTab = <Manage {...this.state} {...this}></Manage>;
        break;
      default:
        renderTab = <Feed />;
        break;
    }
    return (
      <Layout style={{ minHeight: "100vh" }}>
        <Header className="header">
          <div>
            <Row align="middle">
              <Col span={14} push={8}>
                <Title style={{ color: "white" }} level={2}>
                  PE/RE Application System
                </Title>
              </Col>
              <Col span={8} pull={14}>
                <img width={75} src={"../../iiitb.png"} />
              </Col>
            </Row>
          </div>
        </Header>
        <Layout>
          <Sider collapsible collapsed={collapsed} onCollapse={this.onCollapse}>
            <div>
              <Menu
                theme="dark"
                defaultSelectedKeys={["1"]}
                mode="inline"
                defaultSelectedKeys={[this.selected_tab]}
              >
                <Menu.Item
                  key="home"
                  icon={<HomeOutlined />}
                  onClick={(e) => {
                    this.onTabChange(e);
                  }}
                >
                  Home
                </Menu.Item>
                <Menu.Item
                  key="application"
                  icon={<ProjectOutlined />}
                  onClick={(e) => {
                    this.onTabChange(e);
                  }}
                >
                  Application
                </Menu.Item>
                <Menu.Item
                  key="manage_app"
                  icon={<ProjectOutlined />}
                  onClick={(e) => {
                    this.onTabChange(e);
                  }}
                >
                  Manage Application
                </Menu.Item>
                <SubMenu icon={<SettingOutlined />} title="Settings">
                  <Menu.Item key="logout" onClick={this.logout}>
                    Logout
                  </Menu.Item>
                </SubMenu>
              </Menu>
            </div>
          </Sider>
          <Content>{renderTab}</Content>
        </Layout>
      </Layout>
    );
  }
}
