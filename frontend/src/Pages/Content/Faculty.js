import Column from "antd/lib/table/Column";
import React, { Component } from "react";
import { Col, Row, Table, Button, Input, Form, Typography } from "antd";
import Layout, { Content, Header } from "antd/lib/layout/layout";

const { Search } = Input;

export default class Faculty extends Component {
  constructor(props) {
    super(props);
    this.state = {
      columns: [],
      data: [],
      filtered: [],
    };
  }

  componentDidMount = () => {
    let url = sessionStorage.getItem("proxy")+"dashboard/Faculty"
    console.log(url)
    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    }).then((res) => res.json())
      .then((response) => {
        this.setState({data: response, filtered: response})
      }).catch((err) => console.log(err));
  };

  onSearch = (value) => {
    let data = this.state.data;

    if (value.length === 0) {
      this.setState({ filtered: data });
      return;
    }
    let searchLower = value.toLowerCase();
    let filtered_projects = data.filter((item) => {
      console.log(item.professor);
      if (item.name.toLowerCase().includes(searchLower)) {
        return true;
      }
      return false;
    });
    console.log(filtered_projects);
    this.setState({ filtered: filtered_projects });
  };

  onSearchChange = (e) => {
    if (e.target.value.length === 0) {
      this.onSearch("");
    }
  };

  render() {
    const { filtered } = this.state;

    return (
      <Layout>
        <Header style={{ backgroundColor: "whitesmoke" }}>
          <Search
            placeholder="Search by Professor Name"
            allowClear
            style={{ width: "80%", marginTop: "15px", marginLeft: "8%" }}
            onSearch={this.onSearch}
            name="professorSearch"
            onChange={this.onSearchChange}
          />
        </Header>
        <Content>
          <Table dataSource={filtered}>
            <Column key="name" dataIndex={"name"} title="Professor Name" />
            <Column
              key="designation"
              dataIndex={"designation"}
              title="Designation"
            />
            <Column
              key="research"
              dataIndex="research"
              title="Research Interest"
            />
            <Column key="email" dataIndex="email" title="Email ID" />
            <Column key="phone" dataIndex="phone" title="Contact No." />
            <Column key="office" dataIndex="office" title="Cabin No." />
            <Column
              key="details"
              render={(r) => {
                return (
                  <Button type="primary" href={r.website}>
                    Details
                  </Button>
                );
              }}
            />
          </Table>
        </Content>
      </Layout>
    );
  }
}
