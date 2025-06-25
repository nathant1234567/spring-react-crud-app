import React, { useState} from "react";
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from "reactstrap";
import { Link } from "react-router-dom";

const AppNavbar = () => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to={"/"}>Home</NavbarBrand>
            <NavbarToggler onClick={() => setIsOpen(!isOpen)} />
            <Collapse isOpen={isOpen} navbar>
                <Nav className="justify-content-end" style={{ width: "100%" }} >
                <NavItem>
                    <NavLink href="https://x.com/NathanT01532681">@NathanT01532681</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="https://github.com/nathant1234567">GitHub</NavLink>
                </NavItem>
                </Nav>
            </Collapse>
        </Navbar>
    );
};

export default AppNavbar;