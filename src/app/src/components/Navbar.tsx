import { FlexBox, Title, Icon, Link, Button } from "@ui5/webcomponents-react";
const navbarItems = [
  {
    name: "Home",
    link: "#",
  },
  {
    name: "Jobs",
    link: "#",
  },
  {
    name: "Internships",
    link: "#",
  },
  {
    name: "Workshop",
    link: "#",
  },
  {
    name: "Courses",
    link: "#",
  },
];

export default function Navbar() {
  return (
    <FlexBox
      justifyContent="SpaceBetween"
      alignItems="Center"
      style={{
        padding: "20px",
      }}
    >
      <Title level="H4" style={{ color: "cornflowerblue" }}>
        Career Connect
      </Title>
      <FlexBox
        justifyContent="Center"
        alignItems="Center"
        style={{ gap: "40%" }}
      >
        {navbarItems.map((navItem: any) => (
          <Title level="H5">
            <Link href={navItem.link} key={navItem.name}>
              {navItem.name}
            </Link>
          </Title>
        ))}
      </FlexBox>

      <div style={{ display: "flex", alignItems: "center", gap: "1rem" }}>
        <Button design="Emphasized">LOGIN</Button>
        <div
          style={{
            border: "2px solid gray",
            padding: "5px",
            borderRadius: "3rem",
          }}
        >
          <Icon
            name="employee"
            style={{ height: "30px", width: "34px" }}
            interactive
          />
        </div>
      </div>
    </FlexBox>
  );
}
