import Column from "antd/lib/table/Column";
import React, { Component } from "react";
import { Col, Row, Table, Button, Input, Form, Typography } from "antd";
import Layout, { Content, Header } from "antd/lib/layout/layout";

const { Search } = Input;

let cols = [
  {
    title: "Professor Name",
    dataIndex: "professor",
  },
  {
    title: "Designation",
    dataIndex: "designation",
  },
  {
    title: "Research Interest",
    dataIndex: "research_interest",
  },
  {
    title: "Email ID",
    dataIndex: "email_id",
  },
  {
    title: "Cabin No.",
    dataIndex: "cabin",
  },
  {
    title: "Details",
    dataIndex: "details",
  },
];

let prof_data = [
  {
    key: 1,
    professor: "Shrisha Rao",
    designation: "Assistant Professor",
    research_interest:
      "renewable energy, intelligent transportation systems, computational sustainability, agent-based modeling, artificial intelligence",
    email_id: "srao-AT-iiitb.ac.in",
    cabin: "A-208",
    details: "https://www.iiitb.ac.in/faculty/shrisha-rao",
  },
  {
    key: 2,
    professor: "Sarika Vadodariya",
    designation: "Assistant Professor",
    research_interest:
      "renewable energy, intelligent transportation systems, computational sustainability, agent-based modeling, artificial intelligence",
    email_id: "srao-AT-iiitb.ac.in",
    cabin: "A-208",
    details: "https://www.iiitb.ac.in/faculty/shrisha-rao",
  },
  {
    key: 3,
    professor: "Shrisha Rao",
    designation: "Assistant Professor",
    research_interest:
      "renewable energy, intelligent transportation systems, computational sustainability, agent-based modeling, artificial intelligence",
    email_id: "srao-AT-iiitb.ac.in",
    cabin: "A-208",
    details: "https://www.iiitb.ac.in/faculty/shrisha-rao",
  },
];

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
    this.setState({ data: prof_data, columns: cols, filtered: prof_data });
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
      if (item.professor.toLowerCase().includes(searchLower)) {
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
    const { data, filtered } = this.state;

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
            <Column key="professor" dataIndex={"professor"} title="Professor" />
            <Column
              key="designation"
              dataIndex={"designation"}
              title="Designation"
            />
            <Column
              key="research_interest"
              dataIndex="research_interest"
              title="Research Interest"
            />
            <Column key="email_id" dataIndex="email_id" title="Email ID" />
            <Column key="cabin" dataIndex="cabin" title="Cabin No." />
            <Column
              key="details"
              render={(r) => {
                return (
                  <Button type="primary" href={r.details}>
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
